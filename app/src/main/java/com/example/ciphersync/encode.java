package com.example.ciphersync;

class MessageEncoder {
    public static String encodeMessage(String message) {
        String initializer = "111111111";
        StringBuilder encodedMessage = new StringBuilder();

        // Convert each character of the message to binary
        for (char c : message.toCharArray()) {
            String binaryChar = Integer.toBinaryString(c);
            // Pad with leading zeros to ensure 8 bits
            binaryChar = String.format("%8s", binaryChar).replace(' ', '0');
            encodedMessage.append(binaryChar);
        }

        // Add the initializer to the beginning of the encoded message
        encodedMessage.insert(0, initializer);

        return encodedMessage.toString();
    }

    public static void main(String[] args) {
        String originalMessage = "Hello, World!";
        String encodedMessage = encodeMessage(originalMessage);
        System.out.println("Encoded message: " + encodedMessage);
    }
}