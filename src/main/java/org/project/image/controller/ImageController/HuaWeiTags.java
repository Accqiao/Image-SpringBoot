package org.project.image.controller.ImageController;

import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.image.v2.*;
import com.huaweicloud.sdk.image.v2.model.*;
import com.huaweicloud.sdk.image.v2.region.ImageRegion;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HuaWeiTags {
    private String OPath = "src/main/resources/Image/";
    public void getTags(String path){
        //        图像数据，base64编码，
        //        要求base64编码后大小不超过10M，
        //        最短边至少15px，最长边最大4096px，
        //        支持JPG/PNG/BMP格式。
        String result = toHuaWei(OPath+path);
        System.out.println(result);
    }


    public String toHuaWei(String path) {
        String ak = "77ZSSPQNULRCCHOKGJG2";
        String sk = "e4OxmMATFgKV0d3FM8h3BI3uOjE8ZYr8LbmfLnzW";
        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);
        ImageClient client = ImageClient.newBuilder()
                .withCredential(auth)
                .withRegion(ImageRegion.valueOf("cn-north-4"))
                .build();
        RunImageTaggingRequest request = new RunImageTaggingRequest();
        ImageTaggingReq body = new ImageTaggingReq();
        body.withLimit(10);
        body.withThreshold(95f);
        body.withLanguage("zh");
        body.withImage(base64(path));
//        图像数据，base64编码，要求base64编码后大小不超过10M，
//        最短边至少15px，最长边最大4096px，支持JPG/PNG/BMP格式。
//        body.withUrl("");
        request.withBody(body);
        try {
            RunImageTaggingResponse response = client.runImageTagging(request);
            System.out.println(response.toString());

            return response.toString();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
        return "no";
    }


    public  String base64(String path) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        //        System.out.println("本地图片转换Base64:" +
        //                encoder.encode(Objects.requireNonNull(data)));
        return encoder.encode(data);
    }
}
