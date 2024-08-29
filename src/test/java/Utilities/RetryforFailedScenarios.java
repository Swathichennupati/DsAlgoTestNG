package Utilities;

public class RetryforFailedScenarios {
	
	    private static final int MAX_RETRY_COUNT = 7; 

	    public static void retry(Runnable testLogic) {
	        int retryCount = 0;
	        boolean passed = false;

	        while (retryCount < MAX_RETRY_COUNT && !passed) {
	            try {
	                testLogic.run();
	                passed = true;
	            } catch (Exception e) {
	                retryCount++;
	                System.out.println("Retrying test: Attempt " + retryCount);
	                if (retryCount >= MAX_RETRY_COUNT) {
	                    throw e; 
	                }
	            }
	        }
	    }
	}

