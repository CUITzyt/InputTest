
LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
# This is the target being built.
LOCAL_PACKAGE_NAME :=  InputTest
# Only compile source java files in this apk.
LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_RESOURCE_DIR += $(LOCAL_PATH)/res
LOCAL_AAPT_FLAGS := --auto-add-overlay
LOCAL_AAPT_FLAGS += --extra-packages com.squareup.leakcanary
LOCAL_RESOURCE_DIR += $(LOCAL_PATH)/../leakcanarylib/res
LOCAL_SRC_FILES += $(call all-java-files-under, ../leakcanarylib/src/)
#LOCAL_SRC_FILES += $(call all-java-files-under, ../leakcanary-1.5/leakcanary-android/src/main/java)
#LOCAL_SRC_FILES += $(call all-java-files-under, ../leakcanary-1.5/leakcanary-watcher/src/main/java)
#LOCAL_SRC_FILES += $(call all-java-files-under, ../leakcanary-1.5/leakcanary-android-gen/src/main/java)
#LOCAL_PROGUARD_ENABLED := disabled
#LOCAL_PACKAGE_NAME := HelloActivity


# Link against the current Android SDK.
#LOCAL_SDK_VERSION := current

# Also link against our own custom library.
LOCAL_JAVA_LIBRARIES :=  framework skyworthdigital 
LOCAL_STATIC_JAVA_LIBRARIES := android-support-v4 ant


LOCAL_PROGUARD_ENABLED := full
# LOCAL_PROGUARD_FLAG_FILES := proguard.cfg
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_PROGUARD_FLAG_FILES := proguard.flags


# We need to assign platform key to use ServiceManager.addService.
LOCAL_CERTIFICATE := platform

LOCAL_AAPT_FLAGS += -c ldpi -c mdpi -c hdpi -c xhdpi

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := ant:libs/ant.jar

include $(BUILD_MULTI_PREBUILT) 
include $(call all-makefiles-under,$(LOCAL_PATH)) 
