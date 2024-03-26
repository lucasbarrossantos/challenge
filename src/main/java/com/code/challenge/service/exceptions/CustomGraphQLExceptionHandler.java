package com.code.challenge.service.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(@NotNull Throwable ex,
                                                @NotNull DataFetchingEnvironment env) {
        ErrorType errorType;

        if (ex instanceof CustomGraphQLException err) {
            if (err.getStatusCode() == HttpStatus.BAD_REQUEST) {
                errorType = ErrorType.BAD_REQUEST;
                return graphQLError(errorType, (CustomGraphQLException) ex, env);
            }
            if (err.getStatusCode() == HttpStatus.NOT_FOUND) {
                errorType = ErrorType.NOT_FOUND;
                return graphQLError(errorType, (CustomGraphQLException) ex, env);
            } else {
                return GraphqlErrorBuilder.newError().build();
            }

        } else if (ex instanceof DataIntegrityViolationException) {
            errorType = ErrorType.BAD_REQUEST;
            return graphQLError(errorType, new CustomGraphQLException(ex.getMessage()), env);
        } else if (ex instanceof ConstraintViolationException violations) {
            errorType = ErrorType.BAD_REQUEST;
            StringBuilder errorMessageBuilder = new StringBuilder();
            violations.getConstraintViolations()
                    .forEach(violation -> errorMessageBuilder
                            .append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; "));
            String errorMessage = errorMessageBuilder.toString();

            return graphQLError(errorType, new CustomGraphQLException(errorMessage), env);
        }
        else {
            return GraphqlErrorBuilder.newError().build();
        }
    }

    private GraphQLError graphQLError(ErrorType errorType, CustomGraphQLException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .errorType(errorType)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}
