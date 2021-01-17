public class ClassLoaderDemo extends ClassLoader {

    public static void main(String[] args) {
        ClassLoaderDemo demo = new ClassLoaderDemo();
        try {
            Class<?> aClass = demo.findClass("Hello");
            Object hello = aClass.newInstance();
            Method method = aClass.getDeclaredMethod("hello");
            method.invoke(hello);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("E:\\workspace\\JAVA_Advanced\\homework\\week_1\\Hello.xlass");
        InputStream fis = null;
        Class<?> helloClass = null;
        try {
            fis = new FileInputStream(file);
            byte[] tt = new byte[fis.available()];
            fis.read(tt);
            byte[] bytes = new byte[tt.length];
            for (int i = 0; i < tt.length; i++) {
                bytes[i] = (byte) (255 - tt[i]);
            }

            helloClass = defineClass(name, bytes, 0, bytes.length);

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return helloClass;
    }
}