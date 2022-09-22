class Solution {
    public String solution(String s) {
        char[]  chars = s.toLowerCase().toCharArray();
        boolean nextUp = false;
        
        chars[0] = Character.toUpperCase(chars[0]);
        
        for (int idx = 0; idx < chars.length; idx++) {
            if (chars[idx] == ' ')  
                nextUp      = true;
            else if (nextUp) {     
                chars[idx]  =  Character.toUpperCase(chars[idx]);
                nextUp      = false;
            }
        }
        
        return new String(chars);
    }
}