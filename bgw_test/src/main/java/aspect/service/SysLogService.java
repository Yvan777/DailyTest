package aspect.service;

import aspect.bo.SysLogBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhe
 * @date 2018/6/4 9:41
 * @email 1529949535@qq.com
 */
@Slf4j
@Service
public class SysLogService {

    public boolean save(SysLogBO sysLogBO){
        // 这里就不做具体实现了
        log.info(sysLogBO.getCreateDate());
        System.out.println("实现业务层");
        return true;
    }
}
