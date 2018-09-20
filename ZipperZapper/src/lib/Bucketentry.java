package lib;

/**
 * The Bucketentry represents a single entry in the Dictionary bucket. It is a
 * linked list where this.next points to another Bucketentry entity.
 * 
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
class Bucketentry <K, V> {
    String keyString;
    Integer valueInteger;
    Integer keyInteger;
    String valueString;
    Bucketentry<K,V> next;
    
    public Bucketentry(String key, Integer value, Bucketentry<K,V> next){
        this.keyString = key;
        this.valueInteger = value;
        this.next = next;
    }
    
    public Bucketentry(Integer key, String value, Bucketentry<K,V> next){
        this.keyInteger = key;
        this.valueString = value;
        this.next = next;
    }
    
    public String getKeyString(){
        return this.keyString;
    }
    
    public Integer getKeyInteger(){
        return this.keyInteger;
    }
}
