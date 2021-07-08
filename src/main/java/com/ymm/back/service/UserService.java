package com.ymm.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private UserRepository userRepository;
//
//    public void deleteAll() {
//        userRepository.deleteAll();
//    }
//
//    public void flush() {
//        userRepository.flush();
//    }

//    @Transactional
//    public void save(UserEntity userEntity) {
//        userRepository.save(userEntity);
//    }

//    public List<LightUserDTO> fetchAllUsers() {
//        List<LightUserDTO> toSend = new ArrayList<>();
//        List<UserEntity> list = userRepository.findAll();
//        list.forEach(user -> toSend.add(new LightUserDTO(user.getId(), user.getFirstName(), user.getLastName())));
//        return toSend;
//    }

//    public String findUsernameWithWsToken(String token) {
//        return userRepository.getUsernameWithWsToken(token);
//    }

//    public UserEntity findByNameOrEmail(String str0, String str1) {
//        return userRepository.getUserByNameOrEMail(str0, str1);
//    }

//    public boolean checkIfUserHasRightToDelete(int userId, int groupIdToCheck) {
//        GroupRoleKey id = new GroupRoleKey(groupIdToCheck, userId);
//        Optional<GroupUser> optional = groupUserJoinService.findById(id);
//        if (optional.isPresent()) {
//            GroupUser groupUser = optional.get();
//            return groupUser.getRole() == 1;
//        }
//        return false;
//    }

//    public String createShortUrl(String firstName, String lastName) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(firstName);
//        sb.append(".");
//        sb.append(Normalizer.normalize(lastName.toLowerCase(), Normalizer.Form.NFD));
//        boolean isShortUrlAvailable = true;
//        int counter = 0;
//        while (isShortUrlAvailable) {
//            sb.append(counter);
//            if (userRepository.countAllByShortUrl(sb.toString()) == 0) {
//                isShortUrlAvailable = false;
//            }
//            counter++;
//        }
//        return sb.toString();
//    }

//    public String findUsernameById(int id) {
//        return userRepository.getUsernameByUserId(id);
//    }
//
//    public UserEntity findById(int id) {
//        return userRepository.findById(id).orElse(null);
//    }

    public String passwordEncoder(String str) {
        return passwordEncoder.encode(str);
    }

    //    public boolean checkIfUserNameOrMailAlreadyUsed(String firstName, String mail) {
//        return userRepository.countAllByFirstNameOrMail(firstName, mail) > 0;
//    }
//    public List<Map<String,Object>> getTest(){ return userRepository.getTest();}


    /**
     * At WebSocket init, fetch user data and map it to a {@link }
     *
     * @param username string value for username
     * @return a {@link }
     */
//    @Transactional
//    public UserEntity getUserInformation(String username) {
//        UserEntity userEntity = findByNameOrEmail(username, username);
//        return userEntity;
//    }
}