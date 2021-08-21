package util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/7/31 14:27
 */
public class DownLoadFileUtil {

    public static final String website = "https://algs4.cs.princeton.edu/11model/";
    public static final String defaultFilePath = "D:\\Java\\project\\a4\\a4-exercise\\src\\main\\resources\\";
    public static final int timeout = -1;
    static final Logger log = Logger.getLogger(DownLoadFileUtil.class.getName());

    /**
     * 下载文件
     *
     * @param savePath
     *         保存的路径
     * @param url
     *         下载文件的url路径
     *
     * @return 文件的path
     */
    @SneakyThrows
    public static String download(String savePath, String url) {
        final HttpResponse httpResponse = HttpRequest.get(url).timeout(timeout).execute();
        final String body = httpResponse.body();

        final String fileName = url.substring(url.lastIndexOf("/") + 1);
        final File file = FileUtil.newFile(savePath + fileName);

        if (!file.exists()) {
            FileUtil.writeString(body, file, Charset.defaultCharset());
            log.info(Thread.currentThread().getName() + "[download file] - {" + fileName + "}");
        }
        
        return file.getPath();
    }

    /**
     * @param url
     *
     * @return 文件的path
     *
     * @see {@link DownLoadFileUtil#download(String, String)}}
     */
    public static String download(String url) {
        return download(defaultFilePath, url);
    }

}
