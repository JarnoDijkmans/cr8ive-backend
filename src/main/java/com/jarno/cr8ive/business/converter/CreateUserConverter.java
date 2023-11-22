//package com.jarno.cr8ive.business.converter;
//
//import com.jarno.cr8ive.business.model.request.user.CreateUserRequestModel;
//import com.jarno.cr8ive.domain.user.impl.PersonalAccount;
//
//public class CreateUserConverter {
//
//    public static PersonalAccount toUserWithHash (CreateUserRequestModel requestModel, String hashedPassword){
//        return PersonalAccount.builder()
//                .firstName(requestModel.getFirstName())
//                .lastName(requestModel.getLastName())
//                .phoneNumber(requestModel.getPhoneNumber())
//                .emailAddress(requestModel.getEmailAddress())
//                .birthday(requestModel.getBirthday())
//                .profilePicture(createUserProfilePicture(requestModel))
//                .userRoles(requestModel.getRole())
//                .location(requestModel.getCountry())
//                .passwordHash(hashedPassword)
//                .build();
//    }
//
//
//    public static String createUserProfilePicture(CreateUserRequestModel requestModel){
//        return requestModel.getProfilePicture().getOriginalFilename();
//    }
//}
