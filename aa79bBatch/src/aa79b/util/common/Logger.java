package aa79b.util.common;

import java.util.Date;

/**
 * @author aresua
 */
public final class Logger {

    /**
     * INSTANCE: Logger.
     * Este campo sirve para la unica instancia del libro de registro
     */
    private static final Logger INSTANCE = new Logger();
    // ***************************
    // ** INSTANCIA DE LA CLASE **
    // ***************************
    /**
     * Este metodo sirve para obtener la unica instancia de la clase.
     * @author omartinez
     * @return La unica instancia de la clase
     */
    public static Logger getInstance() {
        return Logger.INSTANCE;
    }
    /**
     * Un constructor para Logger.
     * @author omartinez
     */
    private Logger() {
        // Empty constructor
    }


    private static final String INICIO_EXCEPCION = "********* INICIO EXCEPCION ********************************************************************************************************************";
    private static final String FIN_EXCEPCION =    "********* FIN    EXCEPCION ********************************************************************************************************************";

    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int DEBUG = 3;

    /**
     *
     * @param level l
     * @param msg m
     */
    public static void batchlog(int level, String msg) {
        Logger.traza(level, "batchlog: " + msg, null, null);
    }

    /**
     *
     * @param level l
     * @param msg m
     * @param o o
     */
    public static void batchlog(int level, String msg, Object o) {
        Logger.traza(level, "batchlog: " + msg, o, null);
    }

    /**
     *
     * @param level l
     * @param msg m
     * @param th th
     */
    public static void batchlog(int level, String msg, Throwable th) {
        Logger.traza(level, "batchlog: " + msg, null, th);
    }

    /**
     *
     * @param level l
     * @param msg m
     * @param o o
     * @param th th
     */
    private static void traza(int level, String msg, Object o, Throwable th) {
        if (level==1){
            Logger.traza(" ERROR ", msg, o, th);
        }
        if (level==2){
            Logger.traza(" INFO  ", msg, o, th);
        }
        if (level==Constants.MAGIC_NUMBER3){
            Logger.traza(" DEBUG ", msg, o, th);
        }
    }

    /**
     *
     * @param strLevel lev
     * @param msg m
     * @param o o
     * @param th th
     */
    private static void traza(String strLevel, String msg, Object o, Throwable th) {
        final Date date = new Date();
        if (o == null){
            if (th == null){
                System.out.println(date + strLevel + msg);
            }else{
                System.out.println(date + strLevel + msg + ", excepcion:" + th);
                Logger.trazaExcepcion(strLevel, th, date);
            }
        }else{
            if (th == null){
                System.out.println(date + strLevel + msg + ", objeto:" + o);
            }else{
                System.out.println(date + strLevel + msg + ", objeto:" + o + ", excepcion:" + th);
                Logger.trazaExcepcion(strLevel, th, date);
            }
        }
    }

    /**
     * @param strLevel lev
     * @param th th
     * @param date d
     */
    private static void trazaExcepcion(String strLevel, Throwable th, Date date) {
        final StackTraceElement[] stack =  th.getStackTrace();
        System.out.println(Logger.INICIO_EXCEPCION);
        for (final StackTraceElement element : stack) {
            System.out.println(date + strLevel + element);
        }
        System.out.println(Logger.FIN_EXCEPCION);
    }
}
