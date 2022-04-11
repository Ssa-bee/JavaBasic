package ch8;

import static ch8.NewExceptionTest.enoughMemory;
import static ch8.NewExceptionTest.enoughSpace;

public class ChainedExceptionEx {
    public static void main(String[] args) {

    }

    static void install() throws InstallException {
        try {
            startInstall();
            copyFiles();
        }catch(SpaceException se){
            InstallException ie = new InstallException("설치 중 예외발생");
            ie.initCause(se);
            throw ie;
        }catch (MemoryException me){
            InstallException ie = new InstallException("설치 중 예외발생");
            ie.initCause(me);
            throw ie;
        }finally{
            deleteTempFiles();
        }
    }

    static void startInstall() throws SpaceException, MemoryException {
        if (!enoughSpace()) {
            throw new SpaceException("설치할 공간이 부족합니다.");
        }
        if (!enoughMemory()) {
            throw new MemoryException("메모리가 부족합니다.");
        }
    }

    static void copyFiles() {
    }

    static void deleteTempFiles() {
    }
}

class InstallException extends Exception {
    InstallException(String msg) {
        super(msg);
    }
}
