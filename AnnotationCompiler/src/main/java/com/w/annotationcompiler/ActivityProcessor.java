package com.w.annotationcompiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class ActivityProcessor extends AbstractProcessor {
    private Filer mFiler; //文件相关的辅助类
    private Messager mMessager; //日志相关的辅助类
    private Elements mElementUtils;
    private String packageName;

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(PageRoute.class.getCanonicalName());
        return types;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mElementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        FieldSpec map = FieldSpec.builder(HashMap.class, "map")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .initializer("new $T<>()", HashMap.class)
                .build();

        //new method
        MethodSpec.Builder getClasses = MethodSpec.methodBuilder("getAnnotatedClasses")
                .addModifiers(Modifier.PUBLIC)
                .returns(Map.class);

        for (Element element : roundEnv.getElementsAnnotatedWith(PageRoute.class)) {

            //collect the classNames who was annotated
            Element clazz = element.getEnclosingElement();
            packageName = clazz.asType().toString();
            //获取类名
            String className = packageName + "." + element.getSimpleName().toString();
            PageRoute pageRoute = element.getAnnotation(PageRoute.class);
            String route = pageRoute.route();

            getClasses.addStatement("map.put(\"" + route + "\",\"" + className + "\")");


//            //获取所在的包名
//            String packageName = mElementUtils.getPackageOf(clazz).asType().toString();
//
//            //完整类名
//            String fullName = clazz.asType().toString();



        }

        getClasses.addStatement("return map");

        //new file
        TypeSpec injectClass = TypeSpec.classBuilder(Router.CLASSNAME)
                .addModifiers(Modifier.PUBLIC)
                .addField(map)
                .addMethod(getClasses.build())
                .build();

        try {
            JavaFile.builder(packageName, injectClass).build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
