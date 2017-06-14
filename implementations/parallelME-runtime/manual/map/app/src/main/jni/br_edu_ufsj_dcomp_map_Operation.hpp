//
// Created by labpi on 05/06/17.
//

#ifndef FILTER_BR_EDU_UFSJ_DCOMP_FILTER_OPERATION_HPP
#define FILTER_BR_EDU_UFSJ_DCOMP_FILTER_OPERATION_HPP
#ifdef __cplusplus
extern "C" {
#endif
void generateVector(int size, float *vector);
JNIEXPORT jlong JNICALL Java_br_edu_ufsj_dcomp_map_Operation_nativeInit(JNIEnv *env, jobject self,jint size);
JNIEXPORT void JNICALL Java_br_edu_ufsj_dcomp_map_Operation_process(JNIEnv *env,jobject self,jobject dataPointerLong);
#ifdef __cplusplus
}
#endif
#endif //FILTER_BR_EDU_UFSJ_DCOMP_FILTER_OPERATION_HPP
