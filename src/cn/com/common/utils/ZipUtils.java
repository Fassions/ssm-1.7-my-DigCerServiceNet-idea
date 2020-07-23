package cn.com.common.utils;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.List;

/**
 * Created by Horace.zhang on 2019/7/4.
 */
public class ZipUtils {
    private static final int  BUFFER_SIZE = 2 * 1024;
        /**
         * 压缩成ZIP 方法1
         * @param srcDir 压缩文件夹路径
         * @param out    压缩文件输出流
         * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
         *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
         * @throws RuntimeException 压缩失败会抛出运行时异常
         */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure)
        throws RuntimeException{
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            zos.setEncoding("gbk");
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

        /**
         * 压缩成ZIP 方法2
         * @param srcFiles 需要压缩的文件列表
         * @param out           压缩文件输出流
         * @throws RuntimeException 压缩失败会抛出运行时异常
         */
    public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            zos.setEncoding("gbk");
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


        /**
         * 递归压缩方法
         * @param sourceFile 源文件
         * @param zos        zip输出流
         * @param name       压缩后的名称
         * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
         *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
         * @throws Exception
         */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }

                }
            }
        }
    }

    /**
     * 迭代删除文件
     * @param dirPath
     */
    public static void deleteDir(String dirPath){
        File file = new File(dirPath);
        if(file.isFile()){
            file.delete();
        }else {
            File[] files = file.listFiles();
            if(files == null){
                file.delete();
            }else {
                for(int i =0;i<files.length;i++){
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }
    //response传出 压缩文件夹
    public static void downLoadFile(String zipName,String filePath,HttpServletResponse response) throws Exception{
        File outFile = null;
        InputStream inputStream = null;
        ServletOutputStream sos = null;
        try{
            outFile = new File(zipName+".zip");
            FileOutputStream fos1 = new FileOutputStream(outFile);
            ZipUtils.toZip(filePath, fos1,true);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/*");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(outFile.getName().getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setDateHeader("Expires", System.currentTimeMillis());
            inputStream = new FileInputStream(outFile);
            sos = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                sos.write(buffer, 0, n);
            }
            sos.flush();
        } finally {
            if (sos != null) {
                sos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outFile != null) {
                outFile.delete();
            }
        }
    }
    //response传出 压缩文件列表
    public static void downLoadFile(String zipName,List<File> files,HttpServletResponse response) throws Exception{
        File outFile = null;
        InputStream inputStream = null;
        ServletOutputStream sos = null;
        try{
            outFile = new File(zipName+".zip");
            
            FileOutputStream fos1 = new FileOutputStream(outFile);
            
            ZipUtils.toZip(files, fos1);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/*");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(outFile.getName().getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setDateHeader("Expires", System.currentTimeMillis());
            inputStream = new FileInputStream(outFile);
            sos = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                sos.write(buffer, 0, n);
            }
            sos.flush();
        } finally {
            if (sos != null) {
                sos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outFile != null) {
                outFile.delete();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        /** 测试压缩方法1  */
        FileOutputStream fos1 = new FileOutputStream(new File("D:\\DigCerServiceNet2\\file\\order\\mytest01.zip"));
        ZipUtils.toZip("D:\\DigCerServiceNet2\\file\\order\\c00a8e7f710a4e39b1f1bac6557eb22a", fos1,true);

//        /** 测试压缩方法2  */
////        List<File> fileList = new ArrayList<>();
////        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/jar.exe"));
////        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/java.exe"));
////        FileOutputStream fos2 = new FileOutputStream(new File("c:/mytest02.zip"));
////        ZipUtils.toZip(fileList, fos2);
        System.out.println("已完成");
    }
}