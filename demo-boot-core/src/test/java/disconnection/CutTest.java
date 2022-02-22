package disconnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.source2.entity.BaseResource;
import com.demo.boot.core.util.CaptchaUtil;
import domain.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class CutTest {

    @Test
    public void testStr(){
        System.out.println(CaptchaUtil.getToken("wyl123","12"));
    }

    @Test
    public void testLog(){
        log.error("{}->Fail to build org base info", "orgName", new Exception());
        log.error("Fail to query brand base info from tyc ->[{}]", "orgName");
        log.error("Fail to query brand base info from tyc ->[{}]", "brandName");
        log.error("[{}] -> adhocQueryEntity error", "aaa");
        log.info("Event processor {} -> process {} events with event type [{}]", "eventHandlerType", "CollectionUtils.size(kgGenerateEvents)", "eventType");
    }

    @Test
    public void testPredicate(){
//        Predicate<String> predicate = s -> s.equals("zhangsan");
//        System.out.println(predicate.test("lisi"));
//        System.out.println(predicate.test("zhangsan"));

        TestVo testVo = new TestVo();
        testVo.setCode("123");
        testVo.setData("asd");
        testVo.setInfo("test");

        Predicate<TestVo> testVoPredicate = testVo1 -> StringUtils.equals(testVo1.getCode(), testVo.getCode())
                || StringUtils.equals(testVo1.getData(), testVo.getData());

        Collection<? extends TestVo> optionalTestVo = commonCollection().stream().filter(testVoPredicate)
                .map(t-> {
                    TestVo testV = new TestVo();
                    testV.setData("ddd");
                    testV.setInfo(t.getInfo());
                    testV.setCode(t.getCode());
                    return testV;
                }).collect(Collectors.toList());
        System.out.println(optionalTestVo);
    }

    @Test
    public void testFunction(){
        Collection<TblPeReportManageLog> collection = new java.util.ArrayList<>(Collections.emptyList());
        TblPeReportManageLog tblPeReportManageLog = new TblPeReportManageLog();
        tblPeReportManageLog.setId(123L);
        tblPeReportManageLog.setRemarks("test");
        collection.add(tblPeReportManageLog);

        Function<TblPeReportManageLog, BaseResource> toCompanyEntity = this::fromCompanyBaseInfo;

        Map<Long, BaseResource> baseResourceMap = collection.stream()
                .filter(e -> StringUtils.isNotBlank(e.getRemarks()))
                .collect(Collectors.toMap(TblPeReportManageLog::getId, toCompanyEntity, (a, b)->a));

        System.out.println(baseResourceMap);
    }

    private BaseResource fromCompanyBaseInfo(TblPeReportManageLog request){
        BaseResource baseResource = new BaseResource();
        baseResource.setParentId(String.valueOf(request.getId()));
        baseResource.setResourceIcon(request.getRemarks());
        return baseResource;
    }

    @Test
    public void testFunction2(){
        String json = "{\"code\": \"000000\",\"info\": \"操作成功\",\"data\": {}}";
        Optional<JSONObject> jsonObject = Optional.ofNullable(JSON.parseObject(json));
        Optional<TestVo> optionalTestVo = jsonObject.map(e -> e.toJavaObject(TestVo.class));
        System.out.println(optionalTestVo.isPresent());
        System.out.println(optionalTestVo.get());
    }

    @Test
    public void testBiConsumer(){
        BiConsumer<TestVo, BaseResource> biConsumer = (testVo, baseResource) -> {
            baseResource.setResourceIcon(testVo.getCode());
            baseResource.setResourceName(testVo.getData());
            baseResource.setResourceCode(testVo.getCode());
        };
        Collection<BaseResource> baseResources = new ArrayList<>();

        commonCollection().forEach(testVo -> {
            BaseResource baseResource = new BaseResource();
            biConsumer.accept(testVo, baseResource);

            baseResources.add(baseResource);
        });

//        commonCollection().forEach(testVo -> {
//            BaseResource baseResource = new BaseResource();
//            baseResource.setResourceCode(testVo.getCode());
//            baseResource.setResourceIcon(testVo.getCode());
//            baseResource.setResourceName(testVo.getData());
//
//            baseResources.add(baseResource);
//        });

        System.out.println(baseResources);
        log.info("[{}] -> test message of biConsumer", baseResources);
    }

    private Collection<TestVo> commonCollection(){
        TestVo testVo2 = new TestVo();
        testVo2.setCode("456");
        testVo2.setData("asd");
        testVo2.setInfo("exe");

        TestVo testVo3 = new TestVo();
        testVo3.setCode("123");
        testVo3.setData("ert");
        testVo3.setInfo("exam");

        Collection<TestVo> testVoList = new ArrayList<>();
        testVoList.add(testVo2);
        testVoList.add(testVo3);
        return testVoList;
    }
}
