#include <jni.h>
#include <string>


extern "C" {
JNIEXPORT jstring JNICALL
Java_com_salem_apps_data_di_NetworkModule_getEncryptedBaseUrl(JNIEnv *env, jobject object) {
    std::string encrypted_base_url = "https://newsapi.org/v2/";
    return env->NewStringUTF(encrypted_base_url.c_str());
  }
}

extern "C" {
JNIEXPORT jstring JNICALL
Java_com_salem_apps_presentation_ui_main_activity_MainViewModel_getEncryptedApiKey(JNIEnv *env, jobject object) {
    std::string getEncryptedApiKey = "d7a42846883b4d88a15a66c5db049ff2";
    return env->NewStringUTF(getEncryptedApiKey.c_str());}
}