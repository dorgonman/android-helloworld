LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_LDLIBS := -llog
  
LOCAL_MODULE    := myNative
LOCAL_SRC_FILES += native.cpp
LOCAL_SRC_FILES += main.cpp
 LOCAL_LDLIBS    := -llog -landroid -lEGL -lGLESv1_CM 
LOCAL_STATIC_LIBRARIES := android_native_app_glue
include $(BUILD_SHARED_LIBRARY)

$(call import-module,android/native_app_glue)