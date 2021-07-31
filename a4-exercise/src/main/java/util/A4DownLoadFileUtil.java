package util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/7/31 14:27
 */
public class A4DownLoadFileUtil {

    public static final String fileNamePrefix = "https://algs4.cs.princeton.edu/11model/";
    public static final String defaultFilePath = "D:\\Java\\project\\a4\\a4-exercise\\src\\main\\resources\\";
    public static final int timeout = -1;

    /**
     * 下载文件
     *
     * @param savePath 保存的路径
     * @param filename 需要下载的文件的名字
     */
    @SneakyThrows
    public static void download(String savePath, String filename) {
        final HttpResponse httpResponse = HttpRequest.get(fileNamePrefix + filename).timeout(timeout).execute();
        final String body = httpResponse.body();

        final File file = FileUtil.newFile(savePath + filename);

        FileUtil.writeString(body, file, Charset.defaultCharset());

        System.out.println("下载完成:" + filename);

    }

    /**
     * @see {@link A4DownLoadFileUtil#download(String, String)}}
     * @param filename
     */
    public static void download(String filename) {
        download(defaultFilePath, filename);
    }

}
