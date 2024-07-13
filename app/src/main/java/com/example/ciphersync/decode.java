package com.example.ciphersync;
class MessageDecoder {
    public static String decodeMessage(String encodedMessage) {
        // Check if the message is valid
        if (!encodedMessage.startsWith("111111111")) {
            return "Invalid message";
        }

        // Remove the initializer
        encodedMessage = encodedMessage.substring(9);

        // Initialize the decoded message
        StringBuilder decodedMessage = new StringBuilder();

        // Iterate over the encoded message in blocks of 8 characters
        for (int i = 0; i < encodedMessage.length(); i += 8) {
            // Extract the 8-character block
            String block = encodedMessage.substring(i, i + 8);

            // Convert the block to an integer
            int asciiValue = Integer.parseInt(block, 2);

            // Convert the integer to a character
            char decodedChar = (char) asciiValue;

            // Append the decoded character to the decoded message
            decodedMessage.append(decodedChar);
        }

        // Return the decoded message
        return decodedMessage.toString();
    }

    public static void main(String[] args) {
        String encodedMessage = "111111111010010000110010101101100011011000110111101101110011011110111011101100110011101110110011011101110111011101100110011101110110";
        String decodedMessage = decodeMessage(encodedMessage);
        System.out.println("Decoded message: " + decodedMessage);
    }
}