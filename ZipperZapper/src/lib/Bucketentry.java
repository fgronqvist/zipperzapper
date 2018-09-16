package lib;

/**
 * The Bucketentry represents a single entry in the Dictionary bucket. It is a
 * linked list where this.next points to another Bucketentry entity.
 * 
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
class Bucketentry <K, V> {
    String key;
    Integer value;
    Bucketentry<K,V> next;
    
    public Bucketentry(String key, Integer value, Bucketentry<K,V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }
    
    public String getKey(){
        return this.key;
    }
}
