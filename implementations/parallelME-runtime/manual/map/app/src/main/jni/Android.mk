
LOCAL_PATH := $(call my-dir)
PM_JNI_PATH := $(call my-dir)/ParallelME


include $(CLEAR_VARS)
LOCAL_MODULE := Operation
LOCAL_C_INCLUDES := $(PM_JNI_PATH)/runtime/include

LOCAL_CPPFLAGS := -Ofast -Wall -Wextra -Werror -Wno-unused-parameter -Wunused-variable -std=c++14 -fexceptions
LOCAL_SHARED_LIBRARIES := ParallelMERuntime

LOCAL_SRC_FILES := br_edu_ufsj_dcomp_map_Operation.cpp

LOCAL_LDLIBS := -llog -ljnigraphics

include $(BUILD_SHARED_LIBRARY)

include $(wildcard $(PM_JNI_PATH)/**/Android.mk)