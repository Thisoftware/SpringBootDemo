package service;

import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.BaseTest;
import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.mapper.TblPeReportManageLogMapper;
import com.demo.boot.core.service.TestService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class ServiceTest extends BaseTest {

    @Resource
    private TestService testService;
    @Resource
    private TblPeReportManageLogMapper manageLogMapper;

    @Test
    public void queryLog(){
        PageInfo<TblPeReportManageLog> pageInfo = testService.queryLog(new QueryLogRequest());
        System.out.println(pageInfo);
    }

    @Test
    public void testMapper(){
        List<QueryLogResponse> responses = testService.queryCustReportLog(new QueryLogRequest()
                .setBusId(545L)
                .setStatus("11,16"));
        System.out.println(responses);
    }

    @Test
    public void testPa(){
        Example example = new Example(TblPeReportManageLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", "sob5187");
        criteria.andEqualTo("peReportManageStatus", "6");
        List<TblPeReportManageLog> list = manageLogMapper.selectByCondition(example);
        System.out.println(list);
    }
}
