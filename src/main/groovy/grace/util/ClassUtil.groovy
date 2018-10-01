package grace.util

class ClassUtil {

    /**
     * 获取类名称
     * @param aClass
     * @return
     */
    static String simpleName(Class aClass) {
        simpleName(aClass.name)
    }

    static String simpleName(String name) {
        name.substring(name.lastIndexOf('.') > 0 ? name.lastIndexOf('.') + 1 : 0)
    }

    /**
     * 获取 class 的默认变量名称
     * @param c
     * @return
     */
    static String propertyName(Class c) { return c.simpleName.uncapitalize() }

    static String propertyName(String name) {
        return name.substring(name.lastIndexOf('.') > 0 ? name.lastIndexOf('.') + 1 : 0).uncapitalize()
    }

    /**
     * 获取包名
     * @param aClass
     * @return
     */
    static String packageName(Class aClass) {
        packageName(aClass.name)
    }

    static String packageName(String name) {
        name.substring(0, name.lastIndexOf('.') > 0 ? name.lastIndexOf('.') : 0)
    }

    /**
     * 获取包路径
     * @param aClass
     * @return
     */
    static String packagePath(Class aClass) {
        packagePath(aClass.name)
    }

    static String packagePath(String name) {
        packageName(name).split('\\.').join(File.separator)
    }

    /**
     * 获取类路径
     * @param aClass
     * @return
     */
    static String classPath(Class aClass) {
        classPath(aClass.name)
    }

    static String classPath(String name) {
        name.split('\\.').join(File.separator)
    }

    /**
     * 是否是 Java/Groovy 类
     * @param file
     * @return
     */
    static boolean isJavaClass(File file) {
        return file.name.endsWith(".java") || file.name.endsWith(".groovy")
    }
}
