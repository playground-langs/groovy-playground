package bradle


import java.util.concurrent.ConcurrentHashMap

abstract class Project extends Script {

    TaskContainer tasks

    Project() {
        tasks = new TaskContainer()
    }

    void repositories(@DelegatesTo(RepositoriesDelegate.class) Closure<RepositoriesDelegate> closure) {
        RepositoriesDelegate delegate = new RepositoriesDelegate()
        delegate.with(closure)
    }

    void dependencies(@DelegatesTo(DependenciesDelegate.class) Closure<DependenciesDelegate> closure) {
        DependenciesDelegate delegate = new DependenciesDelegate()
        delegate.with(closure)
    }
}

class TaskContainer {
    static final Map<String, Closure> taskMap = [:] as ConcurrentHashMap

    void register(String name, Closure task) {
        taskMap[name] = task
    }

    void run(String name) {
        Closure task = taskMap[name]
        TaskDelegate delegate = new TaskDelegate()
        delegate.with(task)
    }
}

class RepositoriesDelegate {
    void mavenCentral() {
        println "call maven central"
    }
}

class DependenciesDelegate {
    void implementation(String id) {
        println("get implementation $id")
    }

    void testImplementation(String id) {
        println("get testImplementation $id")
    }
}

class TaskDelegate {
    void doLast(Closure closure) {
        closure.call()
    }
}
