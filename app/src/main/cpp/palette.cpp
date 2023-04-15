// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("palette");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("palette")
//      }
//    }

#include "jni.h"
#include "android/log.h"

#include "lib/cpp/cam/hct.h"

extern "C" {
using namespace material_color_utilities;

JNIEXPORT jint JNICALL
Java_com_example_palette_HCT_hct(
        JNIEnv *env, jobject thiz,
        jdouble hue, jdouble chroma, jdouble tone
) {
    Hct hct = Hct(hue, chroma, tone);
    Argb argb = hct.ToInt();
    return argb;
}
}