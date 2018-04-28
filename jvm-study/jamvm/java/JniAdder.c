#include "JniAdder.h"

JNIEXPORT jint JNICALL Java_JniAdder_nativeAdd 
(JNIEnv * env, jobject obj, jint x, jint y){
        return x+y;
}