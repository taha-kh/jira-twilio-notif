import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.atlassian.connect.spring.AtlassianHostUser;

@Controller
public class IssueController {
	
	
	//== Rest Controllers ==
		@PostMapping("/issue-created")
		public void postIssueCreated() {						
			System.out.println("\"I am Here mother fuckerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr----------------------------------------------******************************** POST\")");
		}

}
