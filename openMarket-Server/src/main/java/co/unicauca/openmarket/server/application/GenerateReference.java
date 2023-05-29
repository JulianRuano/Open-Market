package co.unicauca.openmarket.server.application;

import java.util.Random;

/**
 *
 * @author Julian Ruano
 */
public  class  GenerateReference {
    public static class Reference{
        public static String getReference() {
            String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String numbers = "0123456789";
            Random random = new Random();
            StringBuilder codigo = new StringBuilder();

            codigo.append(letters.charAt(random.nextInt(letters.length())));  // Letra inicial
            codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Primer dígito
            codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Segundo dígito
            codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Tercer dígito
            codigo.append(letters.charAt(random.nextInt(letters.length())));  // Letra intermedia
            codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Cuarto dígito
            codigo.append(letters.charAt(random.nextInt(letters.length())));  // Letra final
            codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Quinto dígito

            return codigo.toString();
        }
    }
    
}
