package com.ymm.back.controller;

import com.ymm.back.domain.tables.User;
import com.ymm.back.domain.tables.Work;
import com.ymm.back.domain.tables.records.UserRecord;
import com.ymm.back.pojos.UserP;
import com.ymm.back.pojos.WorkP;
import com.ymm.back.service.UserService;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.jooq.impl.DSL.coalesce;
import static org.jooq.impl.DSL.val;

@RestController
@RequestMapping("/user")
public class UserController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserController(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
    }
    //유저정보를 모두 받아오면 보안이 X된다.. getAll 메소드는 삭제.

    // 유저정보 로그인한 후에 id 파라미터로 가져오기
    //localhost:8080/user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> selectUserOne(@PathVariable("id") int id){
        User user=  User.USER;
        /*List<com.ymm.back.domain.tables.pojos.Work>*/
        var result = dslContext.select().from(user).where(user.ID.eq(id)).fetchInto(UserP.class);
        return ResponseEntity.status(200).body(result);//jdbcTemplate.queryForList(sql).get(0);
    }
    // login,
    // POST / localhost:8080/user/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserP input){
        User user = User.USER;
        String result="";
        var sql = dslContext.select().from(user)
                .where(user.EMAIL.eq(input.getEmail()).and(user.PASSWORD.eq(input.getPassword())))
                .fetchInto(UserP.class);
        if(sql.isEmpty()){
            result = "회원이 없습니다.";
            return ResponseEntity.status(401).body("회원이 없거나 잘못된 비밀번호 입니다.");
            // exception 직접 던지는 것은 자제하고 ResponseEntity로 통일중.
            // 복붙할 API를 처음에 잘만들걸...
            //throw new BadCredentialsException("회원이 없거나 잘못된 비밀번호 입니다.");
        }

        return ResponseEntity.status(200).body(sql);
    }
    // 회원가입은 성공시 201로 직접 지정한다.
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserP input){
        User user = User.USER;
        String result="";
        var sql = dslContext.insertInto(user)
                .columns(user.EMAIL,user.ENABLED,user.PASSWORD)
                //지금은 ENABLED가 default로 활성화에 유의!
                //나중에 spring security 적용시에는 전적으로 시큐리티 모듈에 맡겨야됨.
                .values(input.getEmail(),true,input.getPassword())
                .execute();
        if(sql ==1){
            result = "회원가입에 성공했습니다. 로그인 해주세요.";
        } else {
            return ResponseEntity.status(409).body("아이디가 중복됩니다. 다른 아이디를 사용해 주세요.");
        }
        return ResponseEntity.status(201).body(result);
    }
    // 회원정보 수정
    // 테이블에 있는 not null 조건상,
    // 업데이트에서도 필수로 id, name, password가 필요함에 유의.
    // 물론 보안상 심히 이상하므로, 나중에 nullCheck로 set 칼럼 빼는 기능 추가할 것...
    // localhost:8080/user/2
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UserP input){
        User user = User.USER;
        String result="";
        Map<String, ?> map1 = new HashMap<>();
        var sql = dslContext.update(user)
                .set(user.EMAIL, input.getEmail())
                .set(user.PASSWORD,input.getPassword())
                .set(user.JOB_TITLE,input.getJobTitle())
                .set(user.NAME,input.getName())
                .set(user.PROFILE,input.getProfile())
                .set(user.ROLE,input.getRole())
                .set(user.TEL,input.getTel())
                .where(user.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "회원정보가 수정되었습니다.";
        } else {
            return ResponseEntity.status(400).body("허용되는 타입의 정보로 입력해서 수정해 주세요.");
        }
        return ResponseEntity.status(201).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id,@RequestBody UserP input){
        User user = User.USER;
        String result="";
        var sql = dslContext.deleteFrom(user)
                .where(user.ID.eq(id).and(user.PASSWORD.eq(input.getPassword())))
                .execute();
        if(sql ==1){
            result = "그동안 저희 서비스를 이용해 주셔서 감사합니다.";
        } else {
            return ResponseEntity.status(400).body("허용되는 타입의 정보를 입력해서 수정해 주세요.");
        }
        return ResponseEntity.status(200).body(result);
    }




        /*
        for(String key : map1.keySet()){
            if(map1.isEmpty()){

            }
        }
        */
//        UserRecord rec = new UserRecord();
//        rec.from(input);
//        rec.reset(input.getName());
//        rec.reset(input.getPassword());
//        rec.reset(input.getTel());
//        rec.reset(input.getJobTitle());
    //rec.update();
    //dslContext.update(user).set(dslContext.newRecord(user,userP)).where(user.ID.eq(id)).execute();
//      var sql =dslContext.executeUpdate(rec, user.ID.eq(id));




//    private final UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

//    @GetMapping
//    public List<Map<String, Object>> selectUser() {
//
//        //Map<String, Object> result = new HashMap<>();
//       // int a = jooqBoardService.list();
//        List<Map<String, Object>> result= userService.selectUser();
//
//        //result.put("list", jooqBoardService.list());
//        //() -> result;
//        return result;
//
//    }
//    @GetMapping("/list")
//    public List<Map<String, Object>> getList(){
//        List<Map<String, Object>> result= userService.getList();
//        return result;
//    }
//    @PostMapping("/a")
//    public ResponseEntity<User> insertUser(){
//        ResponseEntity<User> result= userService.insertUser();
//        return result;
//    }
//    @PatchMapping("/change")
//    public String updateUser(){
//        String result = userService.updateUser();
//        return result;
//    }
//    @DeleteMapping("/out")
//    public String deleteUser(){
//        String result = userService.deleteUser();
//        return result;
//    }

}
