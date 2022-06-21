package tutorial;

import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.util.BambooServer;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;

/**
 * Plan configuration for Bamboo.
 * Learn more on: <a href="https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs">https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs</a>
 */
@BambooSpec
public class PlanSpec {

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
                .name("BuilderSpec_CucumberSample")
                .key("BSCSJF");
    }

    Plan createPlan() {
        return new Plan(
                project(),
                "BSCSJF_PLAN", "BSCSJFKEY")
                .description("Plan created from (enter repository url of your plan)");
    }


}
