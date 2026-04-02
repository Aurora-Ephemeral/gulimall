package com.mall.spzx.manager.service.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.mall.spzx.manager.utils.TestDataLoader;
import com.mall.spzx.model.dto.system.SysUserDto;
import com.mall.spzx.model.entity.system.SysUser;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisPlusTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class SystemUserMapperTest {
    @Resource
    private SystemUserMapper systemUserMapper;

    @MockBean
    private RedisTemplate<String, String> redisTemplate;

    private String RESOURCE_PATH = "testdata/sysusermapper/data.json";

    @Test
    @DisplayName("test add user")
    void testInsertUser() {

        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        systemUserMapper.addUser(user);

        SysUser selectUser = systemUserMapper.selectUserInfoByUserName("zhangsan_666");
        assertNotNull(user.getId());
        assertEquals(user.getUserName(), selectUser.getUserName());
    }

    @Test
    @DisplayName("test update user")
    void testUpdateUser() {
        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        systemUserMapper.addUser(user);

        user.setUserName("lisi_888");
        systemUserMapper.updateUser(user);

        SysUser selectUser = systemUserMapper.selectUserInfoByUserName("lisi_888");
        assertEquals(user.getUserName(), selectUser.getUserName());
    }

    @Test
    @DisplayName("test delete user")
    void testDeleteUser() {
        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        systemUserMapper.addUser(user);

        SysUser selectUser = systemUserMapper.selectUserInfoByUserName("zhangsan_666");
        systemUserMapper.deleteUser(selectUser.getId());

        SysUser selectUser2 = systemUserMapper.selectUserInfoByUserName("zhangsan_666");
        assertNotNull(selectUser);
        assertNull(selectUser2);

    }

    @Test
    @DisplayName("test get user list")
    void testGetUserList() {
        SysUserDto sysUserDto = new SysUserDto();
        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        SysUser user2 = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user2");
        systemUserMapper.addUser(user);
        systemUserMapper.addUser(user2);
        List<SysUser> userList = systemUserMapper.getListByPage(sysUserDto);
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
        assertEquals(2, userList.size());
    }

    @Test
    @DisplayName("test get user list by user name")
    void testGetUserListByUserName() {
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setKeyword("admin");
        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        SysUser user2 = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user2");
        systemUserMapper.addUser(user);
        systemUserMapper.addUser(user2);
        List<SysUser> userList = systemUserMapper.getListByPage(sysUserDto);
        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals("admin", userList.get(0).getUserName());
    }

    @Test
    @DisplayName("test getListByPage: return null when user name is not in database")
    void shouldReturnNull_testGetUserListByUserName() {
        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        systemUserMapper.addUser(user);
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setKeyword("lisi_888");
        List<SysUser> userList = systemUserMapper.getListByPage(sysUserDto);
        assertNotNull(userList);
        assertTrue(userList.isEmpty());
    }

    @Test
    @DisplayName("test select user by user name")
    void testSelectUserByUserName() {
        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        systemUserMapper.addUser(user);

        SysUser selectUser = systemUserMapper.selectUserInfoByUserName("zhangsan_666");
        assertNotNull(selectUser);
        assertEquals("zhangsan_666", selectUser.getUserName());
    }

    @Test
    @DisplayName("test selectUserInfo: should return null when user name is not in database")
    void shouldReturnNull_testSelectUserByUserName() {
        SysUser user = TestDataLoader.loadAs(RESOURCE_PATH, SysUser.class, "new_user1");
        systemUserMapper.addUser(user);

        SysUser selectUser = systemUserMapper.selectUserInfoByUserName("lisi_888");
        assertNull(selectUser);
    }
}
