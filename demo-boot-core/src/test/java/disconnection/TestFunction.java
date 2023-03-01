package disconnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.source2.entity.BaseResource;
import com.demo.boot.core.util.CaptchaUtil;
import domain.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class TestFunction {

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

    /**
     * java.util.function 包下的，分别是Consumer（消费型）、supplier（供给型）、predicate（谓词型）、function（功能性）
     * Predicate是个布尔判断接口
     */
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

    /**
     * Function接口是一个功能型接口，它的一个作用就是转换作用，将输入数据转换成另一种形式的输出数据
     */
    @Test
    public void testFunction(){
        Collection<TblPeReportManageLog> collection = new java.util.ArrayList<>(Collections.emptyList());
        TblPeReportManageLog tblPeReportManageLog = new TblPeReportManageLog();
        tblPeReportManageLog.setId(123L);
        tblPeReportManageLog.setRemarks("test");
        collection.add(tblPeReportManageLog);

        Function<TblPeReportManageLog, BaseResource> toCompanyEntity = tblPeReportManage -> {
            BaseResource baseResource = new BaseResource();
            baseResource.setParentId(String.valueOf(tblPeReportManage.getId()));
            baseResource.setResourceIcon(tblPeReportManage.getRemarks());
            return baseResource;
        };

        Map<Long, BaseResource> baseResourceMap = collection.stream()
                .filter(e -> StringUtils.isNotBlank(e.getRemarks()))
                .collect(Collectors.toMap(TblPeReportManageLog::getId, toCompanyEntity, (a, b)->a));

        System.out.println(baseResourceMap);
    }

    @Test
    public void testFunction2(){
        String json = "{\"code\": \"000000\",\"info\": \"操作成功\",\"data\": {}}";
        Optional<JSONObject> jsonObject = Optional.ofNullable(JSON.parseObject(json));
        Optional<TestVo> optionalTestVo = jsonObject.map(e -> e.toJavaObject(TestVo.class));
        System.out.println(optionalTestVo.isPresent());
        System.out.println(optionalTestVo.get());
    }

    /**
     * consumer接口就是一个消费型的接口，通过传入参数，然后输出值
     * 除此之外还有IntConsumer、DoubleConsumer、LongConsumer、BiConsumer等接口
     */
    @Test
    public void testBiConsumer(){
        //Consumer使用
        List<String> strings = new ArrayList<>();
        Consumer<String> consumer1 = s -> strings.add(s + "T");

        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer1);
        System.out.println(strings);

        //BiConsumer使用
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

        log.info("{} -> test message of biConsumer", baseResources);
    }

    /**
     * Supplier用做容器，可以用来存储数据，然后可以供其他方法使用的这么一个接口
     */
    @Test
    public void testSupplier(){
        Stream<Integer> stream = Stream.of(1,2,3,4,5);
        //System.out.println(stream.filter(i -> i > 5).findFirst().orElse(1));

        Supplier<Integer> supplier = () -> new Random().nextInt();
        System.out.println(stream.filter(i -> i > 5).findFirst().orElseGet(supplier));
    }

    @Test
    public void testTuple(){
        Pair<Boolean, String> pair = Pair.of(true, "bbb");
        System.out.println(pair);

        Triple<Boolean, String, List<String>> triple = new ImmutableTriple<>(false, "aa", Lists.newArrayList("a", "b"));
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());
    }

    @Test
    public void optional(){
        TestVo testVo = new TestVo();
        testVo.setCode("456");

        Optional.ofNullable(testVo).ifPresent(p ->{
            p.setInfo("bbb");
            p.setData("ddd");
            p.setNum(111);
        });

        System.out.println(Optional.ofNullable(testVo.getNum()).orElseGet(() -> new Random().nextInt()));

        System.out.println(Optional.ofNullable(testVo.getNum()).orElse(1));

        Optional.ofNullable(testVo.getInfo()).orElseThrow(() -> new RuntimeException("--------withdrawAuditRecord is not find, caseNumber："));

        System.out.println(Optional.ofNullable(testVo).get());

        System.out.println(Optional.of(testVo).get());

        System.out.println(Optional.ofNullable(testVo).map(TestVo::getNum).orElse(0));
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
