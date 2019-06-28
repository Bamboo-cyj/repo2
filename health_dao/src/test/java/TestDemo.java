import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *
 */
public class TestDemo {
    public static void main(String[] args) throws IOException {
        HttpServletRequest request=null;
        HttpServletResponse response=null;


        String fileName = request.getParameter("fileName");
        System.out.println("fileName=" + fileName);

// 设置两头一流(文件下载类型和指示客户端下载文件)
// 告诉浏览器要下载
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

// 告诉浏览器的文件类型
        String type = request.getSession().getServletContext().getMimeType(fileName);
// response.setContentType(type);
        response.setHeader("Content-Type", type);

// 设置文件的输入流
        String realPath = request.getSession().getServletContext().getRealPath("/download/" + fileName);
        InputStream is = new FileInputStream(realPath);
        OutputStream os = response.getOutputStream();

        byte[] b = new byte[1024];
        int len = 0;

        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
    }
}
