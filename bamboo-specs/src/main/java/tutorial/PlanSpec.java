package tutorial;

import java.util.Base64;

import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.builders.repository.git.UserPasswordAuthentication;
import com.atlassian.bamboo.specs.builders.repository.github.GitHubRepository;
import com.atlassian.bamboo.specs.builders.task.CheckoutItem;
import com.atlassian.bamboo.specs.builders.task.CleanWorkingDirectoryTask;
import com.atlassian.bamboo.specs.builders.task.MavenTask;
import com.atlassian.bamboo.specs.builders.task.VcsCheckoutTask;
import com.atlassian.bamboo.specs.util.BambooServer;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.Variable;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;

/**
 * Plan configuration for Bamboo.
 * Learn more on: <a href="https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs">https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs</a>
 */
@BambooSpec
public class PlanSpec {
	
	static String repoUrl = "CucumberSel";
	static String repoName= "CucumberJunitParallel";
	static String env = "dev";
	static String username = "c2hpdnNoaXZlMDRAeWFob28uY29t";
	static String password = "U2hpdmU3NDQ4JA==";

    /**
     * Run main to publish plan on Bamboo
     */
    public static void main(final String[] args) throws Exception {
        //By default credentials are read from the '.credentials' file.
        BambooServer bambooServer = new BambooServer("http://localhost:8085");

        Plan plan = new PlanSpec().createPlan();

        bambooServer.publish(plan);

        PlanPermissions planPermission = new PlanSpec().createPlanPermission(plan.getIdentifier());

        bambooServer.publish(planPermission);
    }

    PlanPermissions createPlanPermission(PlanIdentifier planIdentifier) {
        Permissions permission = new Permissions()
                .userPermissions("admin", PermissionType.ADMIN, PermissionType.CLONE, PermissionType.EDIT)
                .groupPermissions("bamboo-admin", PermissionType.ADMIN)
                .loggedInUserPermissions(PermissionType.VIEW)
                .anonymousUserPermissionView();
        return new PlanPermissions(planIdentifier.getProjectKey(), planIdentifier.getPlanKey()).permissions(permission);
    }

    Project project() {
        return new Project()
                .name("MyBambooBuildSpecProject")
                .key("MYBBSPRJ123");
    }

    Plan createPlan() {
        return new Plan(
                project(),
                "BSCSJF_PLAN", "BSCSJFKEY123")
                .description("Plan created from through bamboo spec")
                .enabled(true)
                .variables(new Variable("env", env))
                .linkedRepositories(repoUrl)
                .stages(new Stage("Stage1123").jobs(new Job("JOB1","JOBKEY123")	
                		.tasks(
                				new CleanWorkingDirectoryTask(),
                				new VcsCheckoutTask()
                					.checkoutItems(new CheckoutItem().defaultRepository()).cleanCheckout(true),
                				new MavenTask().enabled(true).executableLabel("maven 3").jdk("JDK 1.8").goal("mvn clean test -Denv=${env}");
                				)))
    }


}
