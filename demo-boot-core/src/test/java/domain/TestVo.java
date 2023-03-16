package domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TestVo {

    private String code;
    private String info;
    private String data;
    private Integer num;
    private Boolean flag;
    private List<Contact> contactList;
    private CreditInfo creditInfo;

    @Data
    public static class Contact{
        @ApiModelProperty(value = "contactName", required = true)
        private String contactName;

        @ApiModelProperty(value = "contactPhone", required = true)
        private String contactPhone;

        @ApiModelProperty(value = "contactType", required = true)
        private Integer contactType;
    }

    @Data
    public static class CreditInfo {

        @ApiModelProperty(value = "pReviewResults", required = true)
        private Integer pReviewResults;

        @ApiModelProperty(value = "rejectNodeId", required = true)
        private String rejectNodeId;

        @ApiModelProperty(value = "ltLevel2", required = true)
        private Integer ltLevel2;

        @ApiModelProperty(value = "stLevel2", required = true)
        private Integer stLevel2;

        @ApiModelProperty(value = "longTermUserType", required = true)
        private Float longTermUserType;

        @ApiModelProperty(value = "shortTermUserType", required = true)
        private Float shortTermUserType;

        @ApiModelProperty(value = "102: small amount, 202: large amount", required = true)
        private Integer pProductCategory;
    }
}
