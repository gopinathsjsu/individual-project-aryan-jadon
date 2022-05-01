package test;

public class CreditCards {

    public static boolean checkCardDetails(String cardDetails) {
        int numberLength = cardDetails.length();

        // card length validation
        if (numberLength > 19){
            return false;
        }

        // checking for Visa Card
        if (numberLength == 13 || numberLength == 16){
            if(cardDetails.charAt(0) == '4'){
                System.out.println("Card Validation Completed -- Visa Card");
                return true;
            }
        }

        // checking for Master Card and Discover
        if (numberLength == 16){
            if(cardDetails.charAt(0) == '5'){
                if (Character.getNumericValue(cardDetails.charAt(1)) >=1 && Character.getNumericValue(cardDetails.charAt(1)) <=5){
                    System.out.println("Card Validation Completed -- Master Card");
                    return true;
                }

                String intialChars = cardDetails.substring(0, 4);

                if(intialChars.equalsIgnoreCase("6011")){
                    System.out.println("Card Validation Completed -- Discover Card");
                    return true;
                }
            }
        }

        // checking for Master Card and Discover
        if (numberLength == 15) {
            if (cardDetails.charAt(0) == '3') {
                if (cardDetails.charAt(1) == '4' || cardDetails.charAt(1) == '7')
                {
                    System.out.println("Card Validation Completed -- Amex Card");
                    return true;
                }
            }
        }

        return false;
    }

}
