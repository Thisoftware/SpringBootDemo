package disconnection;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

/**
 * @author kis
 * @since 2023/05/08 14:37:22
 */
@Data
public class AppsFly {

    @Alias(value = "AppsFlyerId")
    private String appsflyerId;

    @Alias(value = "GoogleDeviceId")
    private String deviceId;

    @Alias(value = "UserId")
    private String userId;
}
