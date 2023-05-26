package disconnection;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

/**
 * @author kis
 * @since 2023/05/08 14:38:38
 */
@Data
public class GoogleFireBase {

    @Alias(value = "GoogleDeviceId")
    private String googleDeviceId;

    @Alias(value = "AppVersion")
    private String appVersion;

    @Alias(value = "OsVersion")
    private String osVersion;

    @Alias(value = "SdkVersion")
    private String sdkVersion;
}
