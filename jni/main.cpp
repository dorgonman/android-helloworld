#include <jni.h>
#include <android/log.h>
#include <android_native_app_glue.h>

static JavaVM* g_JavaVM = NULL;



/**
 * This is the main entry point of a native application that is using
 * android_native_app_glue.  It runs in its own thread, with its own
 * event loop for receiving input events and doing other things.
 */




jobject getInstance(JNIEnv* env, jclass obj_class)
{
    jmethodID construction_id = env->GetMethodID(obj_class, "<init>", "()V");
    jobject obj = env->NewObject(obj_class, construction_id);
    return obj;
}
//http://www.iam.ubc.ca/guides/javatut99/native1.1/implementing/method.html
//http://docs.oracle.com/javase/1.5.0/docs/guide/jni/spec/functions.html#wp16660
//http://chnic.iteye.com/blog/228096
//http://www.oracle.com/technetwork/java/index.html
void android_main(struct android_app* state) {
	 // Make sure glue isn't stripped.
	app_dummy();
	 __android_log_print(ANDROID_LOG_DEBUG, "C++", "NDK:LC: [%s]", "Hello NativeActivity");
	 ANativeActivity* pActivity = state->activity;
	 JNIEnv* env = pActivity->env;

	// pActivity->vm->AttachCurrentThread(&env, NULL);




	 jclass clazz = env->GetObjectClass(pActivity->clazz);
	 jobject obj = pActivity->clazz;


	 jmethodID helloStaticCPP_method_static = env->GetStaticMethodID(clazz, "helloStaticCPP", "()V");
	 env->CallStaticVoidMethod(clazz, helloStaticCPP_method_static);



	 jmethodID helloCPP_method = env->GetMethodID(clazz, "helloCPP", "()V");
	 env->CallVoidMethod(obj, helloCPP_method);


	 jmethodID messageMe_method = env->GetMethodID(clazz, "messageMe", "(Ljava/lang/String;)V");
	 jstring str = env->NewStringUTF("helloJava! this is C++");
	 env->CallVoidMethod(obj, messageMe_method, str);
	 env->DeleteLocalRef(str);
	 // 获取ANativeActivity，这个类其实也是调用JAVA的类，这里通过NDK入口的传入参数获取。作用是窗口Activity的

	// pActivity->vm->DetachCurrentThread();
}























/*#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
















int main()
{
	JNIEnv *env;
	JavaVM *jvm;

	JavaVMInitArgs vm_args;
	JavaVMOption options[1];

	// launch Jvm
	options[0].optionString = "-Djava.class.path=."; // add user classes
	vm_args.version = JNI_VERSION_1_6; //JDK version.
	vm_args.options = options;
	vm_args.nOptions = 1;

	if( JNI_CreateJavaVM(&jvm, (void*) &env, &vm_args) < 0 )
	{
		fprintf( stderr , "Launch JVM Error\n" );
		exit(1);
	}

	// find the obj & method
	jclass my_class;
	jmethodID my_main;

	if( !( my_class = (*env)->FindClass( env, "MyJavaClass") ) )
	{
		fprintf( stderr , "'Class' Not Found\n" );
		exit(1);
	}

	if( !( my_main = (*env)->GetStaticMethodID( env, my_class , "main" ,  "([Ljava/lang/String;)V" ) ) )
		fprintf( stderr , "'main' Not Found\n" );
	else	// Call main function
		(*env)->CallStaticVoidMethod( env, my_class, my_main, NULL);

	// finish
	(*jvm)->DestroyJavaVM(jvm);

	return 0;
}

@ MyJavaClass.java

class MyJavaClass
{
	public static void main( String []arg)
	{
		System.out.println("Hello World");
	}
}*/
