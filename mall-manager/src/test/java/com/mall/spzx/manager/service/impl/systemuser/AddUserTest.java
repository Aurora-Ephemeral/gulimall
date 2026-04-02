package com.mall.spzx.manager.service.impl.systemuser;

import com.mall.spzx.common.exception.LoginException;
import com.mall.spzx.manager.utils.TestDataLoader;
import com.mall.spzx.model.entity.system.SysUser;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.util.DigestUtils;

@DisplayName("Add User Test:")
public class AddUserTest extends SystemUserServiceImplBaseTest  {

    private SysUser newUser;
    private SysUser existUser;
    private String RESOURCE_PATH = RESOURCE_DIR + "add_user.json";

    @BeforeEach()
    public void setUp() {
        newUser = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user");
        existUser = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "exist_user");
    }

    @Test
    @DisplayName("add new user successfully")
    public void shouldSuccess_testAddNewUser() {
        // 1. mock dependent methods
        Mockito.doReturn(null).when(systemUserMapper).selectUserInfoByUserName(Mockito.anyString());
        // 2. act
        systemUserServiceImpl.addUser(newUser);
        // 3. validation
        // 3.1 verify method call times
        ArgumentCaptor<SysUser> captor = ArgumentCaptor.forClass(SysUser.class);
        Mockito.verify(systemUserMapper, Mockito.times(1)).addUser(captor.capture());
        // 3.2 verify method call arguments
        String expectedPassWord = DigestUtils.md5DigestAsHex("password123".getBytes());
        Assertions.assertEquals(expectedPassWord, captor.getValue().getPassword());
        // 3.3 verify mapper input parameters
        SysUser captorUser = captor.getValue();
        newUser.setPassword(expectedPassWord);
        assertThat(captorUser).usingRecursiveComparison().isEqualTo(newUser);
    }

    @Test
    @DisplayName("throw exception when add user with same username")
    public void shouldThrow_testAddUserWithSameUsername() {
        // 1. mock dependent methods
        Mockito.doReturn(existUser).when(systemUserMapper).selectUserInfoByUserName(existUser.getUserName());

        // 2. act
        // 3.1 verify exception type
        LoginException ex = Assertions.assertThrows(LoginException.class, () -> systemUserServiceImpl.addUser(existUser));
        // 3.2 verify exception message
        Assertions.assertEquals(ResultCodeEnum.USER_NAME_IS_EXISTS, ex.getResultCodeEnum());
        // 3.3 verify function call
        Mockito.verify(systemUserMapper, Mockito.never()).addUser(Mockito.any(SysUser.class));
        Mockito.verify(systemUserMapper, Mockito.times(1)).selectUserInfoByUserName(existUser.getUserName());
    }

}
