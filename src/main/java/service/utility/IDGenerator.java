package service.utility;

import java.util.UUID;

/**
 * UUID wrapper to generate IDs
 */
public class IDGenerator {
    /**
     * Generates a UUID
     * @return a UUID String
     */
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
