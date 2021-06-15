package service;

import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.core.BaseTest;
import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import com.demo.boot.core.service.TestService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

@Slf4j
public class ServiceTest extends BaseTest {

    @Resource
    private TestService testService;

    @Test
    public void queryLog(){
        PageInfo<TblPeReportManageLog> pageInfo = testService.queryLog(new QueryLogRequest());
        System.out.println(pageInfo);
    }

}
