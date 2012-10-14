#include <jni.h>
#include <string>
#include <android/log.h>
#include <stdlib.h>
#include <vector>
#include <iostream>
#define DEBUG_TAG "NDK_AndroidNDKMainActivity"

#ifdef __cplusplus
extern "C" {
#endif

using namespace std;
void Java_com_horizon_AndroidHelloWorld_MainActivity_helloLog(JNIEnv * env, jobject obj, jstring logThis){
    jboolean isCopy;
    std::vector<std::string> vec;
    cout << "c++ lib" << endl;

    const char * szLogThis = env->GetStringUTFChars(logThis, &isCopy);

    __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, "NDK:LC: [%s]", szLogThis);

    env->ReleaseStringUTFChars(logThis, szLogThis);
}


#ifdef __cplusplus
}
#endif





