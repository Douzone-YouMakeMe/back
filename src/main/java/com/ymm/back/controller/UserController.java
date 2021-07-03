package com.ymm.back.controller;

import com.ymm.back.domain.tables.User;
import com.ymm.back.domain.tables.records.UserRecord;
import com.ymm.back.pojos.UserM;
import com.ymm.back.pojos.UserP;
import com.ymm.back.s3.FileUploadService;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import static org.jooq.impl.DSL.coalesce;
import static org.jooq.impl.DSL.val;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    private final FileUploadService fileUploadService;
    @Autowired
    public UserController(DSLContext dslContext, JdbcTemplate jdbcTemplate, FileUploadService fileUploadService) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
        this.fileUploadService = fileUploadService;
    }
    //유저정보를 모두 받아오면 보안이 X된다.. getAll 메소드는 삭제.

    // 유저정보 필요할때, 받아오는 대표 API.
    // 유저정보 로그인한 후에 id 파라미터로 가져오기에도 사용.
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
    /**
     * 회원가입은 성공시 201로 직접 지정한다.
     * {
     *     "email": "tmdrbs1@gmail.com",
     *     "password": "0000",
     *     "name": "aaa"
     * }
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserP input){
        User user = User.USER;
        String result="";
        var emailchk = "";
        var sql = 0;
        try { //이메일이 있는지 없는지 체크한다.
            emailchk = dslContext.select(DSL.field("email"))
                    .from(user).where(user.EMAIL.eq(input.getEmail())).fetchInto(UserP.class).get(0).getEmail();
            return ResponseEntity.status(409).body("이메일이 중복됩니다. 다른 이메일로 가입 시도 바랍니다.");
            //이메일이 없을때 nullpointer익셉션을 일으켜서 가입 go!
        } catch (Exception e){
            sql = dslContext.insertInto(user)
                    .columns(user.EMAIL,user.NAME,user.ENABLED,user.PASSWORD,user.WSTOKEN)
                    //지금은 ENABLED가 default로 활성화에 유의!
                    //나중에 spring security 키는순간 전적으로 시큐리티 모듈에 맡겨야됨.
                    .values(input.getEmail(),input.getName(),true,input.getPassword(),UUID.randomUUID().toString())
                    .execute();
        }
        if(sql ==1){
            result = "회원가입에 성공했습니다. 로그인 해주세요.";
        }
        return ResponseEntity.status(201).body(result);
    }
    // 회원정보 수정
    // 테이블에 있는 not null 조건상,
    // 업데이트에서도 필수로 id, name, password가 필요함에 유의.
    // 2021.06.29. 일단 프론트에 문의해서 처음에는 GET/user 로 다 받아서 양식 채워진 상태로 보여주기로.
    // 대신 profile은 s3를 쓰는 특성상, 돈 덜나오게 안넣어도 되도록 수정함. --2021.07.01
    // localhost:8080/user/2
    @PutMapping(path="/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @ModelAttribute UserM input){
        User user = User.USER;
        String result="";
        // id name email password tel profile jobTitle Role wstoken jwt role_id
        UserRecord record = dslContext.newRecord(user);
        if(/*!input.profile.isEmpty() &&*/ input.getProfile() !=null){
            record.set(user.PROFILE,fileUploadService.uploadImage(input.getProfile()));
        }
        record.set(user.NAME,input.getName());
        record.set(user.EMAIL, input.getEmail());
        record.set(user.PASSWORD,input.getPassword());
        record.set(user.TEL,input.getTel());
        record.set(user.JOB_TITLE,input.getJobTitle());

        var sql = dslContext.update(user)
                  .set(record)
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
            return ResponseEntity.status(400).body("비밀번호를 정확히 입력해 주세요.");
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
