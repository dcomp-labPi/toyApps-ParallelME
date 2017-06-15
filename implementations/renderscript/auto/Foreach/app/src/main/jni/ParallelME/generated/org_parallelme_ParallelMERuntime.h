/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 */
 
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>

#ifndef _Included_org_parallelme_ParallelMERuntime
#define _Included_org_parallelme_ParallelMERuntime
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jlong JNICALL Java_org_parallelme_ParallelMERuntime_nativeInit
	(JNIEnv *, jobject);

JNIEXPORT void JNICALL Java_org_parallelme_ParallelMERuntime_nativeCleanUpRuntime
	(JNIEnv *, jobject, jlong);

JNIEXPORT jlong JNICALL Java_org_parallelme_ParallelMERuntime_nativeCreateArray__II
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jlong JNICALL Java_org_parallelme_ParallelMERuntime_nativeCreateArray__IILjava_lang_Object_2
  (JNIEnv *, jobject, jint, jint, jobject);
	
JNIEXPORT void JNICALL Java_org_parallelme_ParallelMERuntime_nativeToArray
	(JNIEnv *, jobject, jlong, jobject);

JNIEXPORT jlong JNICALL Java_org_parallelme_ParallelMERuntime_nativeCreateBitmapImage
	(JNIEnv *, jobject, jlong, jobject, jint, jint);

JNIEXPORT void JNICALL Java_org_parallelme_ParallelMERuntime_nativeToBitmapBitmapImage
	(JNIEnv *, jobject, jlong, jlong, jobject);

JNIEXPORT jlong JNICALL Java_org_parallelme_ParallelMERuntime_nativeCreateHDRImage
	(JNIEnv *, jobject, jlong, jbyteArray, jint, jint);

JNIEXPORT void JNICALL Java_org_parallelme_ParallelMERuntime_nativeToBitmapHDRImage
	(JNIEnv *, jobject, jlong, jlong, jobject);

JNIEXPORT jint JNICALL Java_org_parallelme_ParallelMERuntime_nativeGetHeight
	(JNIEnv *, jobject, jlong);

JNIEXPORT jint JNICALL Java_org_parallelme_ParallelMERuntime_nativeGetWidth
	(JNIEnv *, jobject, jlong);

JNIEXPORT jint JNICALL Java_org_parallelme_ParallelMERuntime_nativeGetLength
	(JNIEnv *, jobject, jlong);

int getFilterArrayLength(JNIEnv *, jintArray);

#ifdef __cplusplus
}
#endif
#endif