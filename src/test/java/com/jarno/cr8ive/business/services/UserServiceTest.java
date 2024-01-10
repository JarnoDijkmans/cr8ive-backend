package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IUserRepository;
import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.user.CreateBusinessRequestModel;
import com.jarno.cr8ive.business.model.request.user.CreateModeratorRequestModel;
import com.jarno.cr8ive.business.model.request.user.CreatePersonalUserRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreateUserResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetAllUsersResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetUserResponseModel;
import com.jarno.cr8ive.domain.factory.IRoleFactory;
import com.jarno.cr8ive.domain.factory.IUserFactory;
import com.jarno.cr8ive.domain.user.IUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    IUserRepository gateway;
    StorageService storageService;

    IRoleFactory roleFactory;
    IUserFactory factory;
    UserService userService;
    IUser mockUser;

    @BeforeEach
    public void setUp() {
        gateway = Mockito.mock(IUserRepository.class);
        storageService = Mockito.mock(StorageService.class);
        factory = Mockito.mock(IUserFactory.class);
        roleFactory = Mockito.mock(IRoleFactory.class);
        userService = new UserService(gateway, factory, roleFactory, storageService);

        mockUser = Mockito.mock(IUser.class);
        Mockito.when(factory.createPersonalAccount(anyLong(), anyString(), anyString(), anyString(), anyString(), anyString(), anySet(), anyString())).thenReturn(mockUser);
        Mockito.when(factory.createBusinessAccount(anyLong(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anySet(), anyString())).thenReturn(mockUser);
        Mockito.when(factory.createModeratorAccount(anyLong(), anyString(), anyString(), anyString(), anyString(), anyString(), anySet(), anyString())).thenReturn(mockUser);

        Mockito.doNothing().when(storageService).storeUserProfilePicture(any(IUser.class), any(MultipartFile.class));
    }

    @Test
    void testCreatePersonalAccount_Successfully() throws UserCustomException {
        // Arrange
        MultipartFile sampleFile = createSampleMultipartFile();
        CreatePersonalUserRequestModel requestModel = new CreatePersonalUserRequestModel("jarno", "dijkmans", "jarnodijkmans@gmail.com", "2002-01-12", "test123", sampleFile);

        // Act
        CreateUserResponseModel responseModel = userService.createAccount(requestModel);

        // Assert
        assertNotNull(responseModel);
        verify(gateway, times(1)).save(any(IUser.class));
    }

    @Test
    void testCreateBusinessAccount_Successfully() throws UserCustomException {
        // Arrange
        MultipartFile sampleFile = createSampleMultipartFile();
        CreateBusinessRequestModel requestModel = new CreateBusinessRequestModel("jarno", "dijkmans", "0612137055","jarnodijkmans@gmail.com", "2002-01-12", "test123", sampleFile);

        // Act
        CreateUserResponseModel responseModel = userService.createAccount(requestModel);

        // Assert
        assertNotNull(responseModel);
        verify(gateway, times(1)).save(any(IUser.class));
    }

    @Test
    void testCreateModeratorAccount_Successfully() throws UserCustomException {
        // Arrange
        MultipartFile sampleFile = createSampleMultipartFile();
        CreateModeratorRequestModel requestModel = new CreateModeratorRequestModel("jarno", "dijkmans", "jarnodijkmans@gmail.com", "2002-01-12", "test123", sampleFile);

        // Act
        CreateUserResponseModel responseModel = userService.createAccount(requestModel);

        // Assert
        assertNotNull(responseModel);
        verify(gateway, times(1)).save(any(IUser.class));
    }

    @Test
    void testCreatePersonalAccount_ThrowsException(){
        // Arrange
        MultipartFile sampleFile = createSampleMultipartFile();
        CreatePersonalUserRequestModel requestModel = new CreatePersonalUserRequestModel("jarno", "dijkmans", "jarnodijkmans@gmail.com", "2002-01-12", "test123", sampleFile);

        when(gateway.existsByEmailAddress(anyString())).thenReturn(true);

        // Act and Assert
        assertThrows(UserCustomException.class, () -> userService.createAccount(requestModel));
        verify(gateway, times(1)).existsByEmailAddress(anyString());
    }

    @Test
    void testCreateBusinessAccount_ThrowsException(){
        // Arrange
        MultipartFile sampleFile = createSampleMultipartFile();
        CreateBusinessRequestModel requestModel = new CreateBusinessRequestModel("jarno", "dijkmans", "0612137055","jarnodijkmans@gmail.com", "2002-01-12", "test123", sampleFile);

        when(gateway.existsByEmailAddress(anyString())).thenReturn(true);

        // Act and Assert
        assertThrows(UserCustomException.class, () -> userService.createAccount(requestModel));
        verify(gateway, times(1)).existsByEmailAddress(anyString());
    }

    @Test
    void testGetUsersByName_SuccessFully() throws UserCustomException {
        // Arrange
        String name = "test";

        // Act
        GetAllUsersResponseModel responseModel = userService.getUsersByName(name);

        // Assert
        assertNotNull(responseModel);
        verify(gateway, times(1)).getUsersByName(name);
    }


    @Test
    void testGetUserById_SuccessFully() throws UserCustomException {
        // Arrange
        long id = 60;

        // Mock behavior for repository
        when(gateway.findUserById(id)).thenReturn(mockUser);
        when(mockUser.getId()).thenReturn(id);

        // Act
        GetUserResponseModel responseModel = userService.getUserById(id);

        // Assert
        assertNotNull(responseModel);
        assertEquals(id, responseModel.getId());
        // Other assertions based on mockUser's properties
        verify(gateway, times(1)).findUserById(id);
    }

    private MultipartFile createSampleMultipartFile() {
        return new MockMultipartFile("file", "sample-image.jpg", "image/jpeg", "Sample Image Content".getBytes());
    }
}