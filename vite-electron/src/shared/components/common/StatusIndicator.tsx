import React from 'react';

type StatusType = 'success' | 'warning' | 'error';

interface StatusIndicatorProps {
    status: StatusType;
}

const StatusIndicator: React.FC<StatusIndicatorProps> = ({ status }) => {
    const colorMap: Record<StatusType, string> = {
        success: 'bg-green-500',
        warning: 'bg-yellow-500',
        error: 'bg-red-500',
    };

    return (
        <span className={`inline-block w-3 h-3 rounded-full ${colorMap[status]}`}></span>
    );
};

export default StatusIndicator;