package SearchEngine;

public class By {

    private final InstanceConfigurationBuilder builder;

    public By(InstanceConfigurationBuilder builder){
        this.builder = builder;
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

        public InstanceConfigurationBuilder id(int id){
            return this.builder.byId(id);
        }

        public InstanceConfigurationBuilder name(String name){
            return this.builder.byName(name);
        }
    }
}
