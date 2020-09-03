package SearchEngine;

import Instance.InstanceManager;

import java.lang.reflect.Type;

public class By<T> {

    private final InstanceConfigurationBuilder builder;
    private InstanceManager instanceManager;

    public By(InstanceConfigurationBuilder builder){
        this.builder = builder;
        this.instanceManager = instanceManager;
    }

    public SearchField by(){
        return new SearchField(this.builder);
    }

    public class SearchField
    {
        private final InstanceConfigurationBuilder builder;

        public SearchField(InstanceConfigurationBuilder builder){
            this.builder = builder;
        }

        public InstanceConfigurationBuilder tag(String tag){
            return this.builder.byTag(tag);
        }

        public InstanceConfigurationBuilder priority(int priority){
            return this.builder.byPriority(priority);
        }

        public InstanceConfigurationBuilder id(String id){
            return this.builder.byId(id);
        }

        public InstanceConfigurationBuilder name(String name){
            return this.builder.byName(name);
        }

        public InstanceConfigurationBuilder type(Class<T> classType){
            return this.builder.byType(classType);
        }

        public InstanceConfigurationBuilder interfaceType(Class<T> iFaceType){
            return this.builder.byInterface(iFaceType);
        }

        public InstanceConfigurationBuilder lowPriority(){
            return this.builder.byLowPriority();
        }

        public InstanceConfigurationBuilder highPriority(){
            return this.builder.byHighPriority();
        }

        public InstanceConfigurationBuilder minInvokeCount(){
            return this.builder.byMinInvokeCount();
        }
    }
}
