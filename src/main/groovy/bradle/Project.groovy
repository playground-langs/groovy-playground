package bradle

import org.codehaus.groovy.runtime.DefaultGroovyMethods

import java.util.concurrent.ConcurrentHashMap

abstract class Project extends Script{

    TaskContainer tasks

    Project() {
        tasks=new TaskContainer()
    }

    void repositories(@DelegatesTo(RepositoriesDelegate.class) Closure<RepositoriesDelegate> closure){
        RepositoriesDelegate delegate=new RepositoriesDelegate()
        DefaultGroovyMethods.with(delegate,closure)
    }

    void dependencies(@DelegatesTo(DependenciesDelegate.class) Closure<DependenciesDelegate> closure){
        DependenciesDelegate delegate=new DependenciesDelegate()
        DefaultGroovyMethods.with(delegate,closure)
    }
}

class TaskContainer{
    static final Map<String,Closure> taskMap=[:] as ConcurrentHashMap

    void register(String name,Closure task){
        taskMap[name]=task
    }

    void run(String name){
        Closure task=taskMap[name]
        TaskDelegate delegate=new TaskDelegate()
        task.delegate=delegate
        task.resolveStrategy=Closure.DELEGATE_ONLY
        task.call()
    }
}

class RepositoriesDelegate{
    void mavenCentral(){
        println "call maven central"
    }
}
class DependenciesDelegate{
    void implementation(String id){
        println("get implementation $id")
    }
    void testImplementation(String id){
        println("get testImplementation $id")
    }
}

class TaskDelegate{
    void doLast(Closure closure){
        closure.call()
    }
}
