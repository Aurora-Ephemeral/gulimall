package com.mall.spzx.manager.service.impl.systemuser;

import com.mall.spzx.manager.service.impl.SystemUserServiceImpl;
import com.mall.spzx.manager.service.mapper.SystemUserMapper;
import com.mall.spzx.manager.service.mapper.SystemUserRoleMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

@ExtendWith(MockitoExtension.class)
@DisplayName("SystemUserServiceImplTest")
public class SystemUserServiceImplBaseTest {

    @Mock
    SystemUserMapper systemUserMapper;

    @Mock
    SystemUserRoleMapper systemUserRoleMapper;

    @Mock
    RedisTemplate<String, String> redisTemplate;

    @InjectMocks
    SystemUserServiceImpl systemUserServiceImpl;

    protected static final String RESOURCE_DIR = "testdata/sysuser/";
}
