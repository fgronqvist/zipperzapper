package lib;

/**
 * The Dictionary contains a list of the characters found during
 * compression/decompression. The bucket is populated with Bucketentry enteties.
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class Dictionary {

    private int stringDictionarySize = 0;
    private int integerDictionarySize = 0;
    private int bucketSize = 4096;
    private Bucketentry<String, Integer>[] stringBucket;
    private Bucketentry<Integer, String>[] integerBucket;

    /**
     * Set the bucketsize in bytes. Default is 4096.
     *
     * @param size
     */
    public void setBucketsize(int size) {
        this.bucketSize = size;
    }

    /**
     * Generate the first 255 characters as the known dictionary.
     */
    public void initializeDictionary() {
        this.stringBucket = new Bucketentry[this.bucketSize];
        this.integerBucket = new Bucketentry[this.bucketSize];

        for (int i = 0; i < 256; i++) {
            this.put("" + (char) i);
            this.put(i, "" + (char) i);
        }
    }

    /**
     * Add a new key-value pair to the dictionary. The entries become a linked
     * list by having the bucketentry.next value stored with the next entry with
     * the same hash. The hash is used as the key for the this.bucket
     * entrypoint. Below is a crude graph of a four bucket bucketlist.
     *
     * this.bucket ------- | | ------- |entry| => entry => entry ------- |null |
     * ------- |entry| => entry -------
     *
     * @param key
     * @param value
     */
    public void put(String key) {
        int hash = this.hash(key);
        Bucketentry newEntry = new Bucketentry(key, this.stringDictionarySize, null);

        if (this.stringBucket[hash] == null) {
            // This is the first entry for this bucket
            this.stringBucket[hash] = newEntry;
            this.stringDictionarySize++;
        } else {
            // This bucket has atleast one entry already, so we have to check
            // if the new entry has a key that maches one in the bucket. If it
            // does, that one has to be changed. If an existing key is not found
            // the new entry is added to the end of the list.
            Bucketentry<String, Integer> previous = null;
            Bucketentry<String, Integer> current = this.stringBucket[hash];

            while (current != null) {
                if (current.getKeyString().equals(key)) {
                    // We are storing a new value with an existing key, so the
                    // currently occupying Bucketentry.next has to be placed into
                    // the new one or the linked list will break.
                    if (previous == null) {
                        // We are replacing the first value in the bucket
                        newEntry.next = current.next;
                        this.stringBucket[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next; // get the next one in the linked list
            }
            // If we get here, we have reached the end of the list and the entry
            // is a new one, so it goes on the end of the list.
            // Why previous.next and not current.next? Because the loop before
            // places current as current.next, wich will be null (the litteral 
            // while condition) if we are at the end of the linked list.
            previous.next = newEntry;

            // Update the dictionary size, so that the next value gets the right
            // dictionary place.
            this.stringDictionarySize++;
        }
    }

    /**
     * Add a new key-value pair to the dictionary. The entries become a linked
     * list by having the bucketentry.next value stored with the next entry with
     * the same hash. The hash is used as the key for the this.bucket
     * entrypoint. Below is a crude graph of a four bucket bucketlist.
     *
     * @param key
     * @param value
     */
    public void put(Integer key, String value) {
        int hash = Math.abs(key) % this.bucketSize;
        Bucketentry newEntry = new Bucketentry(key, value, null);

        if (this.integerBucket[hash] == null) {
            // This is the first entry for this bucket
            this.integerBucket[hash] = newEntry;
            this.integerDictionarySize++;
        } else {
            // This bucket has atleast one entry already, so we have to check
            // if the new entry has a key that maches one in the bucket. If it
            // does, that one has to be changed. If an existing key is not found
            // the new entry is added to the end of the list.
            Bucketentry<Integer, String> previous = null;
            Bucketentry<Integer, String> current = this.integerBucket[hash];

            while (current != null) {
                if (current.getKeyInteger().equals(key)) {
                    // We are storing a new value with an existing key, so the
                    // currently occupying Bucketentry.next has to be placed into
                    // the new one or the linked list will break.
                    if (previous == null) {
                        // We are replacing the first value in the bucket
                        newEntry.next = current.next;
                        this.integerBucket[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next; // get the next one in the linked list
            }
            // If we get here, we have reached the end of the list and the entry
            // is a new one, so it goes on the end of the list.
            // Why previous.next and not current.next? Because the loop before
            // places current as current.next, wich will be null (the litteral 
            // while condition) if we are at the end of the linked list.
            previous.next = newEntry;

            // Update the dictionary size, so that the next value gets the right
            // dictionary place.
            this.integerDictionarySize++;
        }
    }

    /**
     * Check if the key exists in the dictionary.
     *
     * @param key
     * @return
     */
    public boolean hasKey(Integer key) {
        int hash = Math.abs(key) % this.bucketSize;
        if (this.integerBucket[hash] == null) {
            // This bucket is empty, so nothing can be found
            return false;
        } else {
            // This bucket has atleast one entry already, so we have to check
            // if the wanted entry exists.
            Bucketentry<Integer, String> current = this.integerBucket[hash];
            while (current != null) {
                if (current.getKeyInteger().equals(key)) {
                    return true;
                }
                current = current.next; // get the next one in the linked list
            }
            // If we get here, we have reached the end of the list and no maching
            // key was found.
            return false;
        }
    }

    /**
     * Find a value based on the key. Returns null if none is found.
     *
     * @param key
     * @return
     */
    public Integer get(String key) {
        int hash = this.hash(key);
        if (this.stringBucket[hash] == null) {
            // This bucket is empty, so no match is possible
            return null;
        } else {
            Bucketentry entry = this.stringBucket[hash];
            while (entry != null) {
                if (entry.getKeyString().equals(key)) {
                    // Found the one we are looking for
                    return entry.valueInteger;
                }
                entry = entry.next;
            }
            return null; // Went thru the whole bucket and none matched.
        }
    }

    /**
     * Find a value based on the key. Returns null if none is found.
     *
     * @param key
     * @return
     */
    public String get(Integer key) {
        int hash = Math.abs(key) % this.bucketSize;
        if (this.integerBucket[hash] == null) {
            // This bucket is empty, so no match is possible
            return null;
        } else {
            Bucketentry entry = this.integerBucket[hash];
            while (entry != null) {
                if (entry.getKeyInteger().equals(key)) {
                    // Found the one we are looking for
                    return entry.valueString;
                }
                entry = entry.next;
            }
            return null; // Went thru the whole bucket and none matched.
        }
    }

    /**
     * Using modulo of the key.hashCode/dictionarySize as the bucket index.
     *
     * @param key
     * @return
     */
    private int hash(String key) {
        return Math.abs(key.hashCode()) % this.bucketSize;
    }

    /**
     * Get the current dictionary size.
     *
     * @return
     */
    public Integer getIntegerDictionarySize() {
        return this.integerDictionarySize;
    }

    /**
     * Get the current dictionary size.
     *
     * @return
     */
    public Integer getStringDictionarySize() {
        return this.stringDictionarySize;
    }

    @Override
    public String toString() {
        String out = null;
        for (int i = 0; i < this.bucketSize; i++) {
            Bucketentry entry = this.integerBucket[i];
            out = out + "\nBucket " + i + ": ";
            out = out + "Entry: " + entry.keyString + ", " + entry.valueInteger;
            if (entry.next != null) {
                Bucketentry next = entry.next;
                while (next != null) {
                    out = out + " => Entry: " + next.keyString + ", " + next.valueInteger;
                    next = next.next;
                }
            }
        }
        return out;
    }

}
