package bradle

import org.codehaus.groovy.control.CompilerConfiguration

def config = new CompilerConfiguration()
config.scriptBaseClass = "Project"
def shell = new GroovyShell(this.class.classLoader, config)

def script = new File("build.bradle")

//return the instance of the scriptBaseClass
def instance = shell.parse(script)

instance.run()

println(this.args)
this.args.each { taskName ->
    instance.tasks.run(taskName)
}